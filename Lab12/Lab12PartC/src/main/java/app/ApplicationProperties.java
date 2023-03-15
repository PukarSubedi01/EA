package app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@ConfigurationProperties(prefix = "application")
@Validated
public class ApplicationProperties {
    @NotEmpty
    private String name;
    @NotEmpty
    private String version;
    @NotEmpty
    private String serverUrl;
    private String serverName;
    private List<String> countries;
    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", serverUrl='" + serverUrl + '\'' +
                ", serverName='" + serverName + '\'' +
                ", countries=" + countries +
                ", user=" + user +
                '}';
    }

    @Valid
    public class User {
        private String firstname;
        private String lastname;
        @NotEmpty
        @Size(min = 8, max = 15)
        private String username;
        @NotEmpty
        @Size(min = 8, max = 15)
        private String password;

        public String getFirstName() {
            return firstname;
        }

        public void setFirstName(String firstName) {
            this.firstname = firstName;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "firstName='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }


}



