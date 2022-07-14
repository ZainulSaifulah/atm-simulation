package com.zainul.atm;

import com.zainul.atm.controller.ApplicationController;

import static com.zainul.atm.util.Configuration.filePath;

public class Application {
    public static void main(String[] args) {
        filePath = args.length != 0 ? args[0] : filePath;
        ApplicationController applicationController = new ApplicationController();

        while (true) {
            applicationController.welcomeScreen();
        }
    }
}
