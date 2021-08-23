package zipFile.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zipFile.model.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long>{
    public Optional<UserDetail> findUserByUserKey(String userKey);
    public List<UserDetail> findAll();    
}