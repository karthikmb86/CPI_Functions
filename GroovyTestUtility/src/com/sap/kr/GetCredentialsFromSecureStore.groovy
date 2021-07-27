import com.sap.it.api.ITApiFactory
import com.sap.it.api.securestore.SecureStoreService
import com.sap.it.api.securestore.UserCredential
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {
    
    def service = ITApiFactory.getApi(SecureStoreService.class, null)
    def credentials = service.getUserCredential("Credential_Name")
    if (credentials == null){
        throw new IllegalStateException("No credential found for alias 'UPLOADED_CREDENTIAL_IN_SECURE_STORE'")
    }
    
    String userName = credentials.getUsername()
    String password = new String(credentials.getPassword())
            
    message.setProperty("userName", userName)
    message.setProperty("password", password)
    return message
}
