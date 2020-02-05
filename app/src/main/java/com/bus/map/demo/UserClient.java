package com.bus.map.demo;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.bus.map.demo.client.OSRMApi;
import com.bus.map.demo.models.User;
import com.github.component.DaggerGitHubAppComponent;
import com.github.component.GitHubAppComponent;
import com.github.component.GitHubContextModule;


public class UserClient extends Application {

    private User user = null;
    public OSRMApi osrmApi;
    private GitHubAppComponent gitHubAppComponent;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        gitHubAppComponent = DaggerGitHubAppComponent.builder().gitHubContextModule(new GitHubContextModule(this)).build();
        osrmApi = gitHubAppComponent.getGitHubApi();

    }
}
