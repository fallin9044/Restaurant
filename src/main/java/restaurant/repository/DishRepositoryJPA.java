package restaurant.repository;

import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restaurant.entity.Dish;
import restaurant.entity.Person;

import java.util.List;
import java.util.Optional;
/**
 * Dish 数据操作类
 * @author Johnieh
 * @date 2019.07.05
 *
 */

public interface DishRepositoryJPA extends JpaRepository<Dish, Long> {

	
	@Modifying
	@Query(value="delete from Dish b where b.dishId =:id")
	public void DeleteById(@Param("id") long id);
	
	
	@Modifying
	@Query(value="Update Dish b set b.dishName=:name,b.dishPrice=:price,b.dishDesc=:desc,b.dishPicture=:pic,b.isrecommend=:rec where b.dishId=:id")
	public void updateDish(@Param("id")long id,@Param("name")String name,@Param("price")int price,@Param("desc") String desc
	                       ,@Param("pic")String pic,@Param("rec")int rec);
	
	@Modifying
	@Query(value="from Dish b where b.dishId = :id")
	public List<Dish> SelectByID(@Param("id")long id);
	
	@Modifying
	@Query(value="from Dish b where b.dishName like %:name%")
	public List<Dish> selectByName(@Param("name")String name);
	
	@Modifying
	@Query(value="from Dish b where b.dishName = :name")
	public List<Dish> findByName(@Param("name")String name);
	
	@Query(value="from Dish b where b.dishName = :name")
	public Optional<Dish> selectADish(@Param("name")String name);
	
	@Modifying
	@Query(value="update Dish d set d.isrecommend = :rec where d.dishId = :id")
	public void updateRecom(@Param("rec")int i,@Param("id")long id);
}
