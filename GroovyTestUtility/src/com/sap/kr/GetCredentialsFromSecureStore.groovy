def Message getCredentials( Message message ) {
    
    def service = ITApiFactory.getApi(SecureStoreService.class, null)
    def credentials = service.getUserCredential("Credential_Name")
    if (credentials == null){
        throw new IllegalStateException("No credential found for alias 'UPLOADED_CREDENTIAL_IN_SECURE_STORE'")
    }
    
    String userName = credentials.getUsername()
    String password = new String(credentials.getPassword())
            
    message.setProperty("userName", userName)
    message.setProperty("password", password)
}
