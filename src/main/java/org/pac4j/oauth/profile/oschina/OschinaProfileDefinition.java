package org.pac4j.oauth.profile.oschina;

import org.pac4j.core.exception.http.HttpAction;
import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.config.OAuthConfiguration;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.definition.OAuthProfileDefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.Token;

/**
 * http://www.oschina.net/openapi/docs/openapi_user
 */
public class OschinaProfileDefinition extends OAuthProfileDefinition {

	public static final String PROFILE_URL = "http://www.oschina.net/action/openapi/user?access_token=%s";
    public static final String NAME = "name";
    public static final String AVATAR_URL = "avatar";
    public static final String URL = "url";

    public OschinaProfileDefinition() {
    	super();
        primary(NAME, Converters.STRING);
        primary(AVATAR_URL, Converters.URL);
        primary(URL, Converters.URL);
    }

	@Override
	public String getProfileUrl(Token accessToken, OAuthConfiguration configuration) {
		return String.format(PROFILE_URL, accessToken.getRawResponse());
	}

	@Override
	public OschinaProfile extractUserProfile(String body) throws HttpAction {
		/*
		 {
		    id: 899**,
		    email: "****@gmail.com",
		    name: "彭博",
		    gender: "male",
		    avatar: "http://www.oschina.net/uploads/user/****",
		    location: "广东 深圳",
		    url: "http://home.oschina.net/****"
		}
		
		获取失败
		{
		    error: "invalid_token",
		    error_description: "Invalid access token: 7fade311-d844-4159-9890-c8f0511337e5"
		}
		 */
		final OschinaProfile profile = new OschinaProfile();
        JsonNode json = JsonHelper.getFirstNode(body);
       /* if (json != null && JsonHelper.getElement(json, "error") == null) {
            profile.setId(JsonHelper.getElement(json, "id"));
            for (final String attribute : getPrimaryAttributes()) {
				convertAndAdd(profile, attribute, JsonHelper.getElement(json, attribute));
			}
        }*/
        return profile;
	}
}