package com.etiya.emojigame.business.constants;

public class Messages {

    public static class AllSuffix {
        public static final String allFetchedFromDatabase = "All fetched from database";

        public static final String getByIdSuffixOfMessages = "Fetched according to id from database";

        public static final String addSuffixOfMessages = "Added to database";
    }


    public static class User {
        public static final String userNotExists = "userNotExists";

        public static final String getAllUsers = "getAllUsers";
        public static final String userAdded = "userAdded";
        public static final String userAlreadyExist = "userAlreadyExist";
        public static final String userNameMustContainLetters = "userNameMustContainLetters";

        public static final String usersAreListedAccordingToTheirPointsAndTimes = "usersAreListedAccordingToTheirPointsAndTimes";

    }

    public static class Question {
        public static final String emojisForRelatedQuestionSuccessfullyFetched = "emojisForRelatedQuestionSuccessfullyFetched";
        public static final String relatedQuestionSuccessfullyAdded = "relatedQuestionSuccessfullyAdded";

        public static final String allEmojisForAllQuestionsSuccessfullyFetched = "allEmojisForAllQuestionsSuccessfullyFetched";

        public static final String allQuestionAreFetchedAccordingToTheirCategory = "allQuestionAreFetchedAccordingToTheirCategory";
    }

    public static class Answer {
        public static final String answerWrong = "answerWrong";
        public static final String rightAnswer = "rightAnswer";
        public static final String questionNotExist = "questionNotExist";
    }

}
