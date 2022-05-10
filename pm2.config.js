module.exports = {
    apps: [
        {
            name: "GitLink",
            script: "/home/pvdbroek/.sdkman/candidates/java/current/bin/java",
            // script: "D:\\Program Files\\Eclipse Adoptium\\jdk-17.0.3.7-hotspot\\bin\\java.exe",
            args:[
                "-jar",
                "./build/libs/GitLink-0.0.1-all.jar",
            ],
            instances: 1,
            autorestart: true,
            watch: false,
            exec_mode: "fork",
            env: {
                GITLINK_ENV: "development"
            },
            env_production: {
                GITLINK_ENV: "production"
            }
        }
    ]
};
