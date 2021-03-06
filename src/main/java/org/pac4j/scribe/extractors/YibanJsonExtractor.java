/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.pac4j.scribe.extractors;

import org.pac4j.scribe.model.YibanToken;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * TODO
 * @author 		： <a href="https://github.com/hiwepy">wandl</a>
 */
public class YibanJsonExtractor extends OAuth2AccessTokenJsonExtractor {

	private static final String USERID_REGEX_PATTERN = "userid";
    private static final String EXPIRES_REGEX_PATTERN = "expires";

    protected YibanJsonExtractor() {
    }

    private static class InstanceHolder {

        private static final YibanJsonExtractor INSTANCE = new YibanJsonExtractor();
    }

    public static YibanJsonExtractor instance() {
        return YibanJsonExtractor.InstanceHolder.INSTANCE;
    }

    
    @Override
    protected OAuth2AccessToken createToken(String accessToken, String tokenType, Integer expiresIn,
    		String refreshToken, String scope, JsonNode response, String rawResponse) {
    	 String userid = extractRequiredParameter(response, USERID_REGEX_PATTERN, rawResponse).asText();
         String unionid = extractRequiredParameter(response, EXPIRES_REGEX_PATTERN, rawResponse).asText();;
         YibanToken token = new YibanToken(accessToken, tokenType, expiresIn, refreshToken, scope, rawResponse, userid);
         return token;
    }
     
}
