package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restaurant.entity.Person;

import java.util.List;

public interface PersonRepositoryJPA extends JpaRepository<Person, Long> {

	// 根据用户账号和密码查询
	@Query(value = "select b from Person b where b.personTele=:tele and b.password=:password and b.personState=1")
	public List<Person> findByTelePassword(@Param("tele") String tele, @Param("password") String password);
	
	@Query(value = "select b from Person b where b.authority = 2 and b.personState = 1")
	public List<Person> findByAuthority();
	
	@Query(value = "select b from Person b where  b.personTele=:telephone")
	public List<Person> findIsRepeat( @Param("telephone") String telephone);
	
	@Query(value = "select b from Person b where b.personTele=:telephone")
	public List<Person> findIsRepeatx(@Param("telephone") String telephone);

	@Modifying
	@Query(value = "update Person b set b.personState=1 where b.id = :id")
	public void waiterIsWork(@Param("id") long id);
	
	@Modifying
	@Query(value = "update Person b set b.personState=0 where b.id = :id")
	public void waiterIsNotWork(@Param("id") long id);
	
	@Modifying
	@Query(value = "update Person b set b.personName=:name,b.sex=:sex,b.personTele=:telephone,b.password=:password "
			+ "where b.id = :id")
	public void editinfo(@Param("id") long id,@Param("name") String name,@Param("sex") int sex,
			@Param("telephone") String telephone,@Param("password") String password);
	
	@Query(value = "select b from Person b where b.personState=1 and b.authority = 2")
	public List<Person> getWorkingPerson();
	
	@Modifying
	@Query(value = "update Person b set b.personTime=CURTIME() where b.id = :id")
	public void waiterWorkTime(@Param("id") long id);

	@Query(value = "select b from Person b where b.id = :id and  to_days(b.personTime) = to_days(now())")
	public List<Person> findByTimeAndId(@Param("id") long id);
	
}
