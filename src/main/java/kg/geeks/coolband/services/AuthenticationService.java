package kg.geeks.coolband.services;

import kg.geeks.coolband.dto.SimpleResponse;
import kg.geeks.coolband.dto.request.ForgotPasswordRequest;
import kg.geeks.coolband.dto.request.SignInRequest;
import kg.geeks.coolband.dto.request.SignUpRequest;
import kg.geeks.coolband.dto.request.UpdateForAdminRequest;
import kg.geeks.coolband.dto.response.AuthenticationSignInResponse;
import kg.geeks.coolband.dto.response.AuthenticationSignUpResponse;
import kg.geeks.coolband.dto.response.UpdateForAdminResponse;
import kg.geeks.coolband.dto.response.UserResponse;

import java.util.List;

public interface AuthenticationService {


    AuthenticationSignInResponse signIn(SignInRequest signInRequest);

    AuthenticationSignUpResponse signUp(SignUpRequest signUpRequest);

    UpdateForAdminResponse updateAdmin(Long id, UpdateForAdminRequest update);

    SimpleResponse changeOnForgot(ForgotPasswordRequest newPasswordRequest);

    List<UserResponse> getAllUser();

    SimpleResponse deleteUserById(Long userId);

    AuthenticationSignUpResponse signUpForManager(SignUpRequest sign);
}