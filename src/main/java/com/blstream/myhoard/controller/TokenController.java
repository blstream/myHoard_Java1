package com.blstream.myhoard.controller;

// TODO RT validation
import com.blstream.myhoard.biz.model.TokenDTO;
import com.blstream.myhoard.biz.model.UserCredentialsDTO;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.biz.service.TokenService;
import com.blstream.myhoard.biz.service.UserService;
import com.blstream.myhoard.constants.Constants;
import com.blstream.myhoard.exception.AuthorizationException;
import com.blstream.myhoard.exception.MyHoardException;
import java.util.UUID;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/oauth/token/")
public class TokenController {

        private final static String GRNAT_TYPE_PASSWORD = "password";
        private final static String GRNAT_TYPE_REFRESH_TOKEN = "refresh_token";

        private static final Logger logger = Logger.getLogger(TokenController.class.getCanonicalName());

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        TokenService tokenService;

        @Autowired
        UserService userService;

        @RequestMapping(method = RequestMethod.POST)
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public TokenDTO newToken(@Valid @RequestBody UserCredentialsDTO credentials) throws MyHoardException {

                TokenDTO tokenDTO = new TokenDTO();

                switch (credentials.getGrantType()) {
                        case GRNAT_TYPE_PASSWORD:
                                tokenDTO = createToken(credentials);
                                break;
                        case GRNAT_TYPE_REFRESH_TOKEN:
                                tokenDTO = refreshToken(credentials);
                                break;
                        default:
                                throw new AuthorizationException(Constants.ERROR_CODE_AUTH_BAD_CREDENTIALS);
                }

                return tokenDTO;
        }

        private TokenDTO createToken(UserCredentialsDTO credentials) throws MyHoardException {
                TokenDTO tokenDTO = new TokenDTO();
                UserDTO userDTO = userService.getUserByEmail(credentials.getUsername());

                if (userDTO != null && !passwordEncoder.matches(credentials.getPassword(), userDTO.getPassword())) {
                        logger.info(String.format("Incorrect password for user: %s", userDTO.getEmail()));
                        throw new AuthorizationException(Constants.ERROR_CODE_AUTH_BAD_CREDENTIALS);
                }

                tokenDTO.setAccessToken(UUID.randomUUID().toString());
                tokenDTO.setExpiresIn(Constants.TOKEN_KEEP_ALIVE_TIME);
                tokenDTO.setRefreshToken(UUID.randomUUID().toString());
                tokenDTO.setEmail(credentials.getUsername());
                
                tokenService.create(tokenDTO);

                return tokenDTO;
        }

        private TokenDTO refreshToken(UserCredentialsDTO credentials) throws MyHoardException {
                TokenDTO tokenDTO = tokenService.getByRefreshToken(credentials.getRefreshToken());

                if (tokenDTO == null || !tokenDTO.getUser().getEmail().equals(credentials.getUsername())) {
                        logger.info(String.format("Incorrect refresh token for user: %s", credentials.getUsername()));
                        throw new AuthorizationException(Constants.ERROR_CODE_AUTH_TOKEN_INVALID);
                }

                tokenDTO.setAccessToken(UUID.randomUUID().toString());
                tokenDTO.setExpiresIn(Constants.TOKEN_KEEP_ALIVE_TIME);
                tokenDTO.setRefreshToken(UUID.randomUUID().toString());
                
                tokenService.update(tokenDTO);

                return tokenDTO;
        }
}
