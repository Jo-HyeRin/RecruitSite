package site.metacoding.miniproject.domain.users;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UsersDao {
	public void insert(Users users);
	public List<Users> findAll();
	public Users findById(Integer id);
	public void update(Users users);
	public void deleteById(Integer id);

	// 로그인
	public Users findByIdAndPassword(@Param("loginId") String loginId, @Param("loginPassword") String loginPassword);
	
	// 아이디 중복체크
	public Integer findByLoginId(String loginId);
}
