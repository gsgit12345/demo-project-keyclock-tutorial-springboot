package com.example.clientkeyclock;

import java.util.ArrayList;
import java.util.List;

import com.example.DTO.RoleDTO;
import com.example.security.KeycloakSecurityUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.common.util.CollectionUtil;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.core.Response;

@RestController
@RequestMapping("/keycloak")
@SecurityRequirement(name = "Keycloak")
public class RoleResource {

    @Autowired
    KeycloakSecurityUtil keycloakUtil;

    @Value("${realm}")
    private String realm;

    @GetMapping(value = "/roles")
    public List<RoleDTO> getRoles() {
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        List<RoleRepresentation> roleRepresentations =
                keycloak.realm(realm).roles().list();
        return mapRoles(roleRepresentations);
    }

    @GetMapping(value = "/roles/{roleName}")
    public RoleDTO getRole(@PathVariable("roleName") String roleName) {
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        return mapRole(keycloak.realm(realm).roles().get(roleName).toRepresentation());
    }

    @PostMapping(value = "/role")
    public Response createRole(RoleDTO role) {
        RoleRepresentation roleRep = mapRoleRep(role);
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        keycloak.realm(realm).roles().create(roleRep);
        return Response.ok(role).build();
    }

    @PutMapping(value = "/role")
    public Response updateRole(RoleDTO role) {
        RoleRepresentation roleRep = mapRoleRep(role);
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        keycloak.realm(realm).roles().get(role.getName()).update(roleRep);
        return Response.ok(role).build();
    }

    @DeleteMapping(value = "/roles/{roleName}")
    public Response deleteUser(@PathVariable("roleName") String roleName) {
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        keycloak.realm(realm).roles().deleteRole(roleName);
        return Response.ok().build();
    }

    public static List<RoleDTO> mapRoles(List<RoleRepresentation> representations) {
        List<RoleDTO> roles = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(representations)) {
            representations.forEach(roleRep -> roles.add(mapRole(roleRep)));
        }
        return roles;
    }

    public static RoleDTO mapRole(RoleRepresentation roleRep) {
        RoleDTO role = new RoleDTO();
        role.setId(roleRep.getId());
        role.setName(roleRep.getName());
        return role;
    }

    public RoleRepresentation mapRoleRep(RoleDTO role) {
        RoleRepresentation roleRep = new RoleRepresentation();
        roleRep.setName(role.getName());
        return roleRep;
    }
}