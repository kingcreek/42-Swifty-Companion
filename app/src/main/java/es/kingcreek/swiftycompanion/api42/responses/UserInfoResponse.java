package es.kingcreek.swiftycompanion.api42.responses;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserInfoResponse {

    @SerializedName("id")
    private int userId;

    @SerializedName("email")
    private String email;

    @SerializedName("login")
    private String login;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("usual_full_name")
    private String usualFullName;

    @SerializedName("usual_first_name")
    private String usualFirstName;

    @SerializedName("url")
    private String url;

    @SerializedName("phone")
    private String phone;

    @SerializedName("displayname")
    private String displayName;

    @SerializedName("kind")
    private String kind;

    @SerializedName("image")
    private ImageInfo image;

    @SerializedName("staff?")
    private boolean staff;

    @SerializedName("correction_point")
    private int correctionPoint;

    @SerializedName("pool_month")
    private String poolMonth;

    @SerializedName("pool_year")
    private String poolYear;

    @SerializedName("location")
    private String location;

    @SerializedName("wallet")
    private int wallet;

    @SerializedName("anonymize_date")
    private String anonymizeDate;

    @SerializedName("data_erasure_date")
    private String dataErasureDate;

    @SerializedName("alumni?")
    private boolean alumni;

    @SerializedName("active?")
    private boolean active;

    @SerializedName("groups")
    private List<Object> groups;

    @SerializedName("cursus_users")
    private List<CursusUser> cursusUsers;

    @SerializedName("projects_users")
    private List<Object> projectsUsers;

    @SerializedName("languages_users")
    private List<LanguagesUser> languagesUsers;

    @SerializedName("achievements")
    private List<Object> achievements;

    @SerializedName("titles")
    private List<Object> titles;

    @SerializedName("titles_users")
    private List<Object> titlesUsers;

    @SerializedName("partnerships")
    private List<Object> partnerships;

    @SerializedName("patroned")
    private List<Patroned> patroned;

    @SerializedName("patroning")
    private List<Object> patroning;

    @SerializedName("expertises_users")
    private List<ExpertisesUser> expertisesUsers;

    @SerializedName("roles")
    private List<Object> roles;

    @SerializedName("campus")
    private List<Campus> campus;

    @SerializedName("campus_users")
    private List<CampusUser> campusUsers;

    public static class ImageInfo {
        @SerializedName("link")
        private String link;

        @SerializedName("versions")
        private Versions versions;

        public static class Versions {
            @SerializedName("large")
            private String large;

            @SerializedName("medium")
            private String medium;

            @SerializedName("small")
            private String small;

            @SerializedName("micro")
            private String micro;

            public String getLarge() {
                return large;
            }

            public String getMedium() {
                return medium;
            }

            public String getSmall() {
                return small;
            }

            public String getMicro() {
                return micro;
            }
        }

        public String getLink() {
            return link;
        }

        public Versions getVersions() {
            return versions;
        }
    }

    public static class CursusUser {
        @SerializedName("id")
        private int id;

        @SerializedName("begin_at")
        private String beginAt;

        @SerializedName("end_at")
        private String endAt;

        @SerializedName("grade")
        private String grade;

        @SerializedName("level")
        private double level;

        @SerializedName("skills")
        private List<Object> skills;

        @SerializedName("cursus_id")
        private int cursusId;

        @SerializedName("has_coalition")
        private boolean hasCoalition;

        @SerializedName("user")
        private User user;

        @SerializedName("cursus")
        private Cursus cursus;

        public static class User {
            @SerializedName("id")
            private int id;

            @SerializedName("login")
            private String login;

            @SerializedName("url")
            private String url;

            public int getId() {
                return id;
            }

            public String getLogin() {
                return login;
            }

            public String getUrl() {
                return url;
            }
        }

        public static class Cursus {
            @SerializedName("id")
            private int id;

            @SerializedName("created_at")
            private String createdAt;

            @SerializedName("name")
            private String name;

            @SerializedName("slug")
            private String slug;

            public int getId() {
                return id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public String getName() {
                return name;
            }

            public String getSlug() {
                return slug;
            }
        }

        public int getId() {
            return id;
        }

        public String getBeginAt() {
            return beginAt;
        }

        public String getEndAt() {
            return endAt;
        }

        public String getGrade() {
            return grade;
        }

        public double getLevel() {
            return level;
        }

        public List<Object> getSkills() {
            return skills;
        }

        public int getCursusId() {
            return cursusId;
        }

        public boolean isHasCoalition() {
            return hasCoalition;
        }

        public User getUser() {
            return user;
        }

        public Cursus getCursus() {
            return cursus;
        }
    }

    public static class LanguagesUser {
        @SerializedName("id")
        private int id;

        @SerializedName("language_id")
        private int languageId;

        @SerializedName("user_id")
        private int userId;

        @SerializedName("position")
        private int position;

        @SerializedName("created_at")
        private String createdAt;

        public int getId() {
            return id;
        }

        public int getLanguageId() {
            return languageId;
        }

        public int getUserId() {
            return userId;
        }

        public int getPosition() {
            return position;
        }

        public String getCreatedAt() {
            return createdAt;
        }
    }

    public static class Patroned {
        @SerializedName("id")
        private int id;

        @SerializedName("user_id")
        private int userId;

        @SerializedName("godfather_id")
        private int godfatherId;

        @SerializedName("ongoing")
        private boolean ongoing;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("updated_at")
        private String updatedAt;

        public int getId() {
            return id;
        }

        public int getUserId() {
            return userId;
        }

        public int getGodfatherId() {
            return godfatherId;
        }

        public boolean isOngoing() {
            return ongoing;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }
    }

    public static class ExpertisesUser {
        @SerializedName("id")
        private int id;

        @SerializedName("expertise_id")
        private int expertiseId;

        @SerializedName("interested")
        private boolean interested;

        @SerializedName("value")
        private int value;

        @SerializedName("contact_me")
        private boolean contactMe;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("user_id")
        private int userId;

        public int getId() {
            return id;
        }

        public int getExpertiseId() {
            return expertiseId;
        }

        public boolean isInterested() {
            return interested;
        }

        public int getValue() {
            return value;
        }

        public boolean isContactMe() {
            return contactMe;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public int getUserId() {
            return userId;
        }
    }

    public static class Campus {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("time_zone")
        private String timeZone;

        @SerializedName("language")
        private Language language;

        @SerializedName("users_count")
        private int usersCount;

        @SerializedName("vogsphere_id")
        private int vogsphereId;

        public static class Language {
            @SerializedName("id")
            private int id;

            @SerializedName("name")
            private String name;

            @SerializedName("identifier")
            private String identifier;

            @SerializedName("created_at")
            private String createdAt;

            @SerializedName("updated_at")
            private String updatedAt;

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getIdentifier() {
                return identifier;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getTimeZone() {
            return timeZone;
        }

        public Language getLanguage() {
            return language;
        }

        public int getUsersCount() {
            return usersCount;
        }

        public int getVogsphereId() {
            return vogsphereId;
        }
    }

    public static class CampusUser {
        @SerializedName("id")
        private int id;

        @SerializedName("user_id")
        private int userId;

        @SerializedName("campus_id")
        private int campusId;

        @SerializedName("is_primary")
        private boolean isPrimary;

        public int getId() {
            return id;
        }

        public int getUserId() {
            return userId;
        }

        public int getCampusId() {
            return campusId;
        }

        public boolean isPrimary() {
            return isPrimary;
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsualFullName() {
        return usualFullName;
    }

    public String getUsualFirstName() {
        return usualFirstName;
    }

    public String getUrl() {
        return url;
    }

    public String getPhone() {
        return phone;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getKind() {
        return kind;
    }

    public ImageInfo getImage() {
        return image;
    }

    public boolean isStaff() {
        return staff;
    }

    public int getCorrectionPoint() {
        return correctionPoint;
    }

    public String getPoolMonth() {
        return poolMonth;
    }

    public String getPoolYear() {
        return poolYear;
    }

    public String getLocation() {
        return location;
    }

    public int getWallet() {
        return wallet;
    }

    public String getAnonymizeDate() {
        return anonymizeDate;
    }

    public String getDataErasureDate() {
        return dataErasureDate;
    }

    public boolean isAlumni() {
        return alumni;
    }

    public boolean isActive() {
        return active;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public List<CursusUser> getCursusUsers() {
        return cursusUsers;
    }

    public List<Object> getProjectsUsers() {
        return projectsUsers;
    }

    public List<LanguagesUser> getLanguagesUsers() {
        return languagesUsers;
    }

    public List<Object> getAchievements() {
        return achievements;
    }

    public List<Object> getTitles() {
        return titles;
    }

    public List<Object> getTitlesUsers() {
        return titlesUsers;
    }

    public List<Object> getPartnerships() {
        return partnerships;
    }

    public List<Patroned> getPatroned() {
        return patroned;
    }

    public List<Object> getPatroning() {
        return patroning;
    }

    public List<ExpertisesUser> getExpertisesUsers() {
        return expertisesUsers;
    }

    public List<Object> getRoles() {
        return roles;
    }

    public List<Campus> getCampus() {
        return campus;
    }

    public List<CampusUser> getCampusUsers() {
        return campusUsers;
    }
}
