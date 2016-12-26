package com.demo.taskapp.Configuration;
import com.demo.taskapp.Controller.TaskController;
import com.demo.taskapp.Controller.UserController;
import com.demo.taskapp.Dao.UserDoa;
import com.demo.taskapp.Dao.UserTaskDao;
import com.demo.taskapp.Models.Task;
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
//        final TemplateHealthCheck healthCheck =
//                new TemplateHealthCheck(configuration.getTemplate());
//        environment.healthChecks().register("template", healthCheck);

//        final HelloWorldResource resource = new HelloWorldResource(
//                configuration.getTemplate(),
//                configuration.getDefaultName()
//        );
        UserDoa userDoa = new UserDoa();
        UserTaskDao userTaskDao = new UserTaskDao();
        final UserController userDaoResource = new UserController(userDoa);
        environment.jersey().register(userDaoResource);
        final TaskController taskControllerResouce = new TaskController(userDoa, userTaskDao);
        environment.jersey().register(taskControllerResouce);

    }
}
