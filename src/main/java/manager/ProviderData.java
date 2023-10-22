package manager;

import models.Contact;
import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderData {

    @DataProvider
    public Iterator<Object[]> userDTO(){  //Data Transfer Object = DTO
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                User.builder()
                        .email("katy@mail.ru")
                        .password("Kk12345!")
                        .build()
        });
        list.add(new Object[]{
                User.builder()
                        .email("katy1@mail.ru")
                        .password("Kk12345!")
                        .build()
        });
        list.add(new Object[]{
                User.builder()
                        .email("katy2@mail.ru")
                        .password("Kk12345!")
                        .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactDTO(){  //Data Transfer Object = DTO
        List<Object[]> list = new ArrayList<>();
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        list.add(new Object[]{
                Contact.builder()
                        .name("Koko_" + i)
                        .lastName("Chanel")
                        .phone("123456" + i)
                        .address("Paris")
                        .discription("Lady")
                        .email("koko_" + i + "@mail.ru")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("Koko_" + i)
                        .lastName("Chanel")
                        .phone("123456" + i)
                        .address("Paris")
                        .discription("Lady")
                        .email("koko_" + i + "@mail.ru")
                        .build()
        });
        return list.iterator();

    }

    @DataProvider
    public Iterator<Object[]> registrationCSV() throws IOException {  //Data Transfer Object = DTO
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(new File("src/test/resources/reg_dataset.csv")));
        String line = reader.readLine();
        while(line != null){
            String[] split = line.split(",");
            list.add(new Object[]{
                    User.builder()
                            .email(split[0])
                            .password(split[1])
                            .build()
            });
            line = reader.readLine();
        }
        reader.close();
        return list.iterator();
    }


}
