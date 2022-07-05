import controller.ApplicationController;

import static util.Configuration.filePath;

public class Application {
    public static void main(String[] args) {
        filePath = args.length != 0 ? args[0] : "./data.csv";
        ApplicationController applicationController = new ApplicationController();

        while (true) {
            applicationController.welcomeScreen();
        }
    }
}
