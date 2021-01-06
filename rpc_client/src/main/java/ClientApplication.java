import lombok.extern.slf4j.Slf4j;
import model.User;
import proxy.RpcClient;
import proxy.RpcClientJdk;
import service.UserService;

/**
 * Client demo.
 *
 * @author bigwillc
 */
@Slf4j
public class ClientApplication {
    public static void main(String[] args) {
        RpcClient jdkProxy = new RpcClientJdk();
        UserService userService = jdkProxy.create(UserService.class, "http://localhost:8080/");
        User user = userService.findById(1);
        if (user == null) {
            log.info("Client service invoke error.");
            return;
        }
        System.out.println("Find user id=1 from server: " + user.getName());
    }
}
