package com.integrative.wishlistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.integrative.wishlistapp.apis.ActivitiesService;
import com.integrative.wishlistapp.apis.InstancesService;
import com.integrative.wishlistapp.apis.RetrofitService;
import com.integrative.wishlistapp.apis.UsersService;
import com.integrative.wishlistapp.model.activity.ActivityBoundary;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.model.user.NewUserBoundary;
import com.integrative.wishlistapp.model.user.UserBoundary;
import com.integrative.wishlistapp.repository.ActivitiesRepository;
import com.integrative.wishlistapp.repository.InstancesRepository;
import com.integrative.wishlistapp.repository.UsersRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsersService usersService = RetrofitService.getInstance().create(UsersService.class);
        InstancesService instancesService = RetrofitService.getInstance().create(InstancesService.class);
        ActivitiesService activitiesService = RetrofitService.getInstance().create(ActivitiesService.class);

        UsersRepository usersRepository = new UsersRepository(usersService);
        InstancesRepository instancesRepository = new InstancesRepository(instancesService);
        ActivitiesRepository activitiesRepository = new ActivitiesRepository(activitiesService);

        String newUser = "{ \"email\": \"t@t.zain\", \"role\": \"PLAYER\", \"username\": \"sfd\", \"avatar\": \"213\" }";
        String user = "";
        String instance = "{ \"instanceId\":{ \"domain\":\"2022b.timor.bystritskie\", \"id\":\"\" }, \"type\":\"dummytype\", \"name\":\"demo instance\", \"active\":\"true\", \"createdTimestamp\":\"2022-02-27T07:55:05.248+0000\", \"createdBy\":{ \"userId\":{ \"domain\":\"2022b.timor.bystritskie\", \"email\":\"t@t.zain\" } }, \"location\":{ \"lat\":32.115139, \"lng\":34.817804 }, \"instanceAttributes\":{ \"key1\":\"can be set to any value you wish\", \"key2\":\"you can also name the attributes any name you like\", \"key3\":4.2, \"key4\":false } }";
        String activity = "{ \"activityId\": { \"domain\": \"2022b.timor.bystritskie\", \"id\": \"\" }, \"type\": \"demotype\", \"instance\": { \"instanceId\": { \"domain\": \"2022b.timor.bystritskie\", \"id\": \"489f43bd-02cd-4598-892e-2cbbe4b5b542\" } }, \"createdTimestamp\": \"2001-07-04T12:08:56.235-0700\", \"invokedBy\": { \"userId\": { \"domain\": \"2022b.timor.bystritskie\", \"email\": \"t@t.zain\" } }, \"activityAttributes\": { \"key1\": \"can be set to any value you wish\", \"key2\": { \"key2Subkey1\": \"can be nested json\" }, \"key3\": \"test\" } }";
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

        NewUserBoundary newUserBoundary =gson.fromJson(newUser, NewUserBoundary.class);
        UserBoundary userBoundary = gson.fromJson(user, UserBoundary.class);
        InstanceBoundary instanceBoundary = gson.fromJson(instance, InstanceBoundary.class);
        ActivityBoundary activityBoundary = gson.fromJson(activity, ActivityBoundary.class);


        MutableLiveData<InstanceBoundary> createdInstance =  instancesRepository.createInstance(instanceBoundary);
        MutableLiveData<UserBoundary> createdUser = usersRepository.createUser(newUserBoundary);
//        activityBoundary.getInstance().setInstanceId(createdInstance.getValue().getInstanceId());
        activitiesRepository.invoke(activityBoundary);

    }
}