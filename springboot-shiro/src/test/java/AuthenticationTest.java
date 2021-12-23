import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import realm.CustomRealm;

public class AuthenticationTest {

    @Test
    public void testAuthentication() {
        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("user", "123456");
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("user", "123456");
        subject.login(token);
        Assertions.assertTrue(subject.isAuthenticated());
        subject.logout();
        Assertions.assertTrue(!subject.isAuthenticated());
    }

    @Test
    public void testCustomRealm() {
        CustomRealm customRealm = new CustomRealm();
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("user", "123456");
        subject.login(token);

        Assertions.assertTrue(subject.isAuthenticated());
        subject.checkRoles("admin", "user");
        subject.checkPermission("user:add");

        Assertions.assertThrows(AuthorizationException.class, () -> {
            subject.checkRoles("superAdmin");
        });

        Assertions.assertThrows(AuthorizationException.class, () -> {
            subject.checkPermission("user:mod");
        });
    }
}
