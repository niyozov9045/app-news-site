package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.dto.ApiResponse;
import uz.pdp.appnewssite.dto.RoleDto;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDto roleDto) {
        if (roleRepository.existsByName(roleDto.getName()))
            return new ApiResponse("Bunday role bor", false);
        Role role = new Role(
                roleDto.getName(),
                roleDto.getPermissonList(),
                roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("Role saqlandi", true);
    }

    public ApiResponse editRole(Long id, RoleDto roleDto) {

        return new ApiResponse("",true);
    }
}
