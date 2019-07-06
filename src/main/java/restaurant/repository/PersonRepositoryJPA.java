package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restaurant.entity.Person;

import java.util.List;

public interface PersonRepositoryJPA extends JpaRepository<Person, Long> {

	// 根据用户账号和密码查询
	@Query(value = "select b from Person b where b.personName=:name and b.password=:password")
	public List<Person> findByNamePassword(@Param("name") String name, @Param("password") String password);
	
	@Query(value = "select b from Person b where b.authority = 2 and b.personState = 1")
	public List<Person> findByAuthority();
	
	@Query(value = "select b from Person b where b.personName=:name and b.personTele=:telephone")
	public List<Person> findIsRepeat(@Param("name") String name, @Param("telephone") String telephone);

	@Modifying
	@Query(value = "update Person b set b.personState=1 where b.id = :id")
	public void waiterIsWork(@Param("id") long id);
	
	@Modifying
	@Query(value = "update Person b set b.personState=0 where b.id = :id")
	public void waiterIsNotWork(@Param("id") long id);
	
	@Query(value = "select b from Person b where b.personState=1")
	public List<Person> getWorkingPerson();


}
