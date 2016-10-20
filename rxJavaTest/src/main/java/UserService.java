import java.util.ArrayList;
import java.util.List;

/**
 * Created by valdeci on 09/09/2016.
 */
public class UserService {

    public List<User> getAllUsers(){

        List list = new ArrayList<User>();
        list.add(new User("zeca","diretor",2000f));
        list.add(new User("hermes","gerente",1000f));
        list.add(new User("valdeci","programador",100f));
        list.add(new User("nathalia","professora",50f));
        list.add(new User("joao","bebe",1f));

        return list;

    }
}
