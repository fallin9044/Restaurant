package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restaurant.entity.Dish;
import restaurant.entity.Person;

import java.util.List;
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
	@Query(value="update Dish b set b.dishID=:id,b.dishName=:name,b.dishPrice=:price,b.dishDesc=:decs,b.dishPicture=:pic,b.isrecommend=:rec")
	public void updateDish(@Param("id")long id,@Param("name")String name,@Param("price")int price,@Param("decs") String decs
	                       ,@Param("pic")String pic,@Param("rec")int rec);
	
	@Modifying
	@Query(value="select b from Dish b where b.dishID = :id")
	public Dish SelectByID(@Param("id")long id);
	
	@Modifying
	@Query(value="select b from Dish b where b.dishName like %:name%")
	public List<Dish> selectByName(@Param("name")String name);
	
	@Modifying
	@Query(value="update Dish set isrecommend = :rec")
	public void updateRecom(@Param("rec")int i);
}
