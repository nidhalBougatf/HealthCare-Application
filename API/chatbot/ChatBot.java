/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package API.chatbot;


import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

/**
 * Text client reads requests line by line from stdandart input.
 */
public class ChatBot {

  
  /**
   * Default exit code in case of error
   */

  
  public String GetResponse(String Text) {

    AIConfiguration configuration = new AIConfiguration("f9d98627f0254bbeb5d33e818e2a8b7d");

    AIDataService dataService = new AIDataService(configuration);

        try {
          AIRequest request = new AIRequest(Text);

          AIResponse response = dataService.request(request);

          if (response.getStatus().getCode() == 200) {
            return response.getResult().getFulfillment().getSpeech();
          } else {
            System.err.println(response.getStatus().getErrorDetails());
          }
        } catch (Exception ex) {
            System.out.println("Chatbot can't connect to internet");
        }

        
     return "Je ne suis pas connecter a l'internet" ;  
      
  }
    
}
