package ru.abr.dit.Checks;

import org.springframework.stereotype.Service;
import ru.abr.dit.Models.User;

import java.util.ArrayList;

@Service
public class EntityCheks {

    public ArrayList<String> checkUser(User user) {

        ArrayList<String> result = new ArrayList<>();

        //проверка уникальности nickname
        String nickname = user.getNickname();



        return result;
    }

}
