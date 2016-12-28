package com.demo.taskapp.Configuration;
import com.demo.taskapp.Resource.TaskResource;
import com.demo.taskapp.Resource.UserResource;
import com.demo.taskapp.Dao.UserDao;
import com.demo.taskapp.Dao.TaskDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by piyush.agarwal on 26/12/16.
 */
public class TaskApplication extends Application<Configuration>{
    public static void main(String[] args) throws Exception {
        new TaskApplication().run(args);
    }

    @Override
    public String getName() {
        return "user-info";
    }


    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(Configuration configuration,
                    Environment environment) {
        UserDao userDoa = new UserDao();
        TaskDao taskDao = new TaskDao();

        final UserResource userResource = new UserResource(userDoa);
        environment.jersey().register(userResource);
        final TaskResource taskResource = new TaskResource(userDoa, taskDao);
        environment.jersey().register(taskResource);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
    }
}
