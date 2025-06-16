package com.example.firstapplication.view

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstapplication.R
import com.example.firstapplication.viewmodel.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun FirstScreen(loginViewModel: LoginViewModel = viewModel()) {

    val context = LocalContext.current

    LaunchedEffect(loginViewModel.loginSuccess) {
        if (loginViewModel.loginSuccess) {
            Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
            loginViewModel.loginSuccess = false
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.log_in_image),
            contentDescription = "Login Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(top = 74.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-130).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = loginViewModel.email,
                onValueChange = {
                    loginViewModel.email = it
                    loginViewModel.emailError= "" },
                label = { Text("Enter your email") },
                isError = loginViewModel.emailError.isNotEmpty(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email Icon"
                    )
                }
            )
            if (loginViewModel.emailError.isNotEmpty()) {
                Text(
                    text = loginViewModel.emailError,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = loginViewModel.password,
                onValueChange = {
                    loginViewModel.password = it
                    loginViewModel.passwordError= "" },
                label = { Text("Enter Password") },
                isError = loginViewModel.passwordError.isNotEmpty(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (loginViewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Password Icon"
                    )
                },
                trailingIcon = {
                    val icon =
                        if (loginViewModel.passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff
                    IconButton(onClick = {
                        loginViewModel.passwordVisible = !loginViewModel.passwordVisible
                    }) {
                        Icon(imageVector = icon, contentDescription = "Toggle Password Visibility")
                    }
                }
            )
            if (loginViewModel.passwordError.isNotEmpty()) {
                Text(
                    text = loginViewModel.passwordError,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { println("Forgot password clicked") }) {
                    Text("Forgot password?")
                }
            }

            Button(
                onClick = {loginViewModel.onLoginClicked()},
                modifier = Modifier.fillMaxWidth(),
                enabled = loginViewModel.isFromValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF252424),
                    disabledContentColor = Color.White.copy(alpha = 0.6f)
                )
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp),
                    color = Color.Gray
                )
                Text(
                    text = " Or Login with ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp),
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { println("Facebook Login")},
                    modifier = Modifier
                        .size(46.dp)
                        .background(Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "Facebook Icon",
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                IconButton(
                    onClick = { println("Google Login")},
                    modifier = Modifier
                        .size(46.dp)
                        .background(Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Google Icon",
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                IconButton(
                    onClick = { println("Twitter Login")},
                    modifier = Modifier
                        .size(46.dp)
                        .background(Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.twitter),
                        contentDescription = "Twitter Icon",
                        tint = Color.Unspecified
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Don't have an account?")
                TextButton(onClick = { println("Register Now clicked") }) {
                    Text("Register Now")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    FirstScreen()
}