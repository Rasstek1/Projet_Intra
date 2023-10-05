package com.martin.projet_intra.models;


    public class ErrorViewModel {
        private String errorCode;
        private String errorMessage;
        private String errorDetails;

        // Constructeur par défaut
        public ErrorViewModel() {}

        // Constructeur avec paramètres
        public ErrorViewModel(String errorCode, String errorMessage, String errorDetails) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
            this.errorDetails = errorDetails;
        }

        // Méthodes d'accès
        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorDetails() {
            return errorDetails;
        }

        public void setErrorDetails(String errorDetails) {
            this.errorDetails = errorDetails;
        }
    }


