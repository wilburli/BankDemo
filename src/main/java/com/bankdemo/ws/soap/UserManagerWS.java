package com.bankdemo.ws.soap;

import com.bankdemo.enums.Role;
import com.bankdemo.exceptions.ApplicationException;
import com.bankdemo.model.user.User;
import com.bankdemo.model.user.UserRole;
import com.bankdemo.util.CxfAutoRegistrationEndpointBean;
import com.bankdemo.util.RoleAdapter;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 */
@WebService(
        name = AccountWS.WS_NAME,
        serviceName = AccountWS.WS_SERVICE_NAME,
        portName = AccountWS.WS_PORT_NAME,
        targetNamespace = AccountWS.WS_NAMESPACE
)
@CxfAutoRegistrationEndpointBean
public interface UserManagerWS {

    String WS_NAME = "UserManager";
    String WS_SERVICE_NAME = WS_NAME + "Service";
    String WS_PORT_NAME = WS_NAME + "Port";
    String WS_NAMESPACE = "http://soap.ws.bankdemo.com/";

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User addUser(
            @WebParam(name = "username") @XmlElement(required = true) String username,
            @WebParam(name = "password") @XmlElement(required = true) String password
    )  throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User updateUser(
            @WebParam(name = "user_id")  @XmlElement(required = true) Integer id,
            @WebParam(name = "username") @XmlElement(required = true) String username,
            @WebParam(name = "password") @XmlElement(required = true) String password
    )  throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteUser(
            @WebParam(name = "user_id") @XmlElement(required = true) Integer id
    )  throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User getUser(
            @WebParam(name = "user_id") @XmlElement(required = true) Integer id
    )  throws ApplicationException;

    @WebMethod
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> getUsers()  throws ApplicationException;

    @WebMethod
    UserRole addUserRole(
            @WebParam(name = "userId") @XmlElement(required = true) Integer userId,
            @WebParam(name = "role") @XmlElement(name = "role", required = true) @XmlJavaTypeAdapter(RoleAdapter.class) Role role
    )  throws ApplicationException;

    @WebMethod
    UserRole updateUserRole(
            @WebParam(name = "roleId") @XmlElement(required = true) Integer roleId,
            @WebParam(name = "userId") @XmlElement(required = true) Integer userId,
            @WebParam(name = "role") @XmlElement(name = "role", required = true) @XmlJavaTypeAdapter(RoleAdapter.class) Role role
    )  throws ApplicationException;
}
