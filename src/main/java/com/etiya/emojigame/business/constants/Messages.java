package com.etiya.emojigame.business.constants;

public class Messages {

    public static class AllSuffix {
        public static final String allFetchedFromDatabase = "All fetched from database";

        public static final String getByIdSuffixOfMessages = "Fetched according to id from database";

        public static final String addSuffixOfMessages = "Added to database";
    }


    public static class User {
        public static final String userNotExists = "User not found";

        public static final String getAllUsers = "All users fetched from database";
        public static final String userAdded = "user successfully added to the database";
        public static final String userAlreadyExist = "user already exist with same name";
        public static final String userNameMustContainLetters = "username must be contain letters";

    }

    public static class Question {
        public static final String emojisForRelatedQuestionSuccessfullyFetched = "emojis for related question successfully fetched";
        public static final String emojisForRelatedQuestionSuccessfullyAdded = "emojis for related question successfully added";

        public static final String allEmojisForAllQuestionsSuccessfullyFetched = "all emojis for all questions are successfully fetched";
    }

}
