package sourcecode.rest.dal.data.user;

import sourcecode.models.other.user.User;
import sourcecode.rest.dal.interfaces.UserContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserLocalData implements UserContext {

    private ArrayList<User> users = new ArrayList<>();

    public UserLocalData(){
        createUsers();
    }

    private void createUsers(){
        User user1 = new User(
                UUID.fromString("a96a8b4a-725f-4078-a957-3de1292d35d5"),
                "Jop",
                "Verhoeven",
                "jopverhoeven",
                "https://4.bp.blogspot.com/-dRrp79RVRLQ/XVMCKwz_zHI/AAAAAAAAHPE/0HWDhBBF5c0pHRLDY02VLhTlZq4YFgiYwCLcBGAs/s1600/selfie-n-d.jpg");

        User user2 = new User(
                UUID.fromString("e7019164-6b27-47ba-b162-cf0d2a07d186"),
                "Dawn",
                "Hillebrand",
                "xDawnH",
                "https://www.abc.net.au/news/image/9298152-3x2-700x467.jpg");

        User user3 = new User(
                UUID.fromString("54bf8482-1a58-4a99-84d5-3c2278147ffa"),
                "Mika",
                "Jonkers",
                "mikarienwim",
                "https://img.maximummedia.ie/her_ie/eyJkYXRhIjoie1widXJsXCI6XCJodHRwOlxcXC9cXFwvbWVkaWEtaGVyLm1heGltdW1tZWRpYS5pZS5zMy5hbWF6b25hd3MuY29tXFxcL3dwLWNvbnRlbnRcXFwvdXBsb2Fkc1xcXC8yMDE3XFxcLzExXFxcLzEyMTQxNjE1XFxcL1NjcmVlbi1TaG90LTIwMTctMTEtMTItYXQtMTQuMTUuMjkucG5nXCIsXCJ3aWR0aFwiOjc2NyxcImhlaWdodFwiOjQzMSxcImRlZmF1bHRcIjpcImh0dHBzOlxcXC9cXFwvd3d3Lmhlci5pZVxcXC9hc3NldHNcXFwvaW1hZ2VzXFxcL2hlclxcXC9uby1pbWFnZS5wbmc_aWQ9MGQyZDYyN2MwNTliOWVkY2FiMGRcIixcIm9wdGlvbnNcIjpbXX0iLCJoYXNoIjoiN2U3YjliMmU2NjgxZjI4YWEzMzNmZDU5Mzg1MDRkNTdiMjM1NzdjZSJ9/screen-shot-2017-11-12-at-14-15-29.png");

        User user4 = new User(
                UUID.fromString("64198b11-17cc-438c-b1f7-23637330d10a"),
                "Bastiaan",
                "Verhaar",
                "iNeedSugar",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyblj_c4-Q_T4pcCtiojbpn8cOtm-1ZJ0OepfpG04M2AxrXD00");

        users.addAll(Arrays.asList(user1, user2, user3, user4));
    }

    @Override
    public User getUserById(UUID userId) {
        User returnUser = null;

        for (User user : users){
            if (user.getUserId().compareTo(userId) == 0){
                returnUser = user;
            }
        }

        return returnUser;
    }

    @Override
    public List<User> getUserByName(String name) {

        List<User> returnList = new ArrayList<>();

        for (User user : users){
            if (user.getFirstname().toLowerCase().contains(name.toLowerCase()) || user.getLastname().toLowerCase().contains(name.toLowerCase()) || user.getUsername().toLowerCase().contains(name.toLowerCase())){
                returnList.add(user);
            }
        }

        return returnList;
    }

    @Override
    public User getUserByUsername(String username) {
        User returnUser = null;

        for (User user : users){
            if (user.getUsername().equalsIgnoreCase(username)){
                returnUser = user;
            }
        }

        return returnUser;
    }
}
