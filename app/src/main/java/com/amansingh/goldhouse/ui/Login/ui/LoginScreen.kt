package com.amansingh.goldhouse.ui.Login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amansingh.goldhouse.R
import com.amansingh.goldhouse.navigation.Destination
import com.amansingh.goldhouse.ui.Login.LoginState
import com.amansingh.goldhouse.ui.Login.LoginViewModel
import com.amansingh.goldhouse.ui.components.GoldHouseLogo
import com.amansingh.goldhouse.ui.theme.GoldHouseColors
import com.amansingh.goldhouse.ui.theme.GoldHouseTheme

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val loginState by viewModel.loginState.collectAsState()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    GoldHouseTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GoldHouseLogo()
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(id = R.string.gold_house_title),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = stringResource(id = R.string.order_management_system),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    modifier = Modifier.padding(top = 4.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))

                GoldHouseEmailInput(
                    value = username,
                    onValueChange = { username = it },
                    enabled = loginState != LoginState.Loading
                )
                Spacer(modifier = Modifier.height(16.dp))

                GoldHousePasswordInput(
                    value = password,
                    onValueChange = { password = it },
                    passwordVisible = passwordVisible,
                    onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                    enabled = loginState != LoginState.Loading
                )
                Spacer(modifier = Modifier.height(16.dp))

                SignInButton(
                    onClick = { viewModel.login(username, password) },
                    isLoading = loginState is LoginState.Loading
                )
            }

            when (val state = loginState) {
                is LoginState.Loading -> {
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is LoginState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(Destination.Home.createRoute(state.user.firstName))
                    }
                }

                is LoginState.Error -> {
                    // You can show a snackbar or a dialog here
                }

                else -> {}
            }
        }
    }
}

@Composable
fun SignInButton(
    onClick: () -> Unit,
    isLoading: Boolean
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        ),
        shape = RoundedCornerShape(16.dp),
        enabled = !isLoading
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (!isLoading) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    fontSize = 18.sp
                )
                Spacer(Modifier.width(16.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            } else {
                Text(
                    text = stringResource(id = R.string.signing_in),
                    fontSize = 18.sp
                )
                Spacer(Modifier.width(16.dp))
                CircularProgressIndicator(modifier = Modifier.size(22.dp), strokeWidth = 2.dp)
            }
        }

    }
}

@Composable
private fun GoldHouseInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    enabled: Boolean = true,
) {
    val goldColor = GoldHouseColors.PrimaryForeground

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(GoldHouseColors.InputBackground, shape = RoundedCornerShape(12.dp)),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = goldColor.copy(alpha = 0.4f),
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = goldColor.copy(alpha = 0.4f),
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onTrailingIconClick?.invoke() }
                )
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                color = goldColor.copy(alpha = 0.5f),
                fontSize = 16.sp
            )
        },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = GoldHouseColors.InputBorder.copy(alpha = 0.3f),
            unfocusedBorderColor = GoldHouseColors.InputBorder.copy(alpha = 0.1f),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = goldColor,
            focusedTextColor = GoldHouseColors.PrimaryForeground,
            unfocusedTextColor = GoldHouseColors.PrimaryForeground,
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        enabled = enabled

    )
}


@Composable
fun GoldHouseEmailInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    GoldHouseInput(
        value = value,
        onValueChange = onValueChange,
        placeholder = stringResource(id = R.string.email_address),
        leadingIcon = Icons.Outlined.Email,
        modifier = modifier,
        enabled = enabled
    )
}

@Composable
fun GoldHousePasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier, passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    enabled: Boolean = true
) {
    // This line selects the icon based on the visibility state
    val image = if (passwordVisible)
        Icons.Filled.Visibility
    else
        Icons.Filled.VisibilityOff

    GoldHouseInput(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = stringResource(id = R.string.password),
        leadingIcon = Icons.Outlined.Lock,
        trailingIcon = image,
        onTrailingIconClick = onPasswordVisibilityChange,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        enabled = enabled
    )

}

@Preview(showBackground = true)
@Composable
fun SignInButtonPreview() {
    GoldHouseTheme {
        SignInButton(onClick = {}, isLoading = true)
    }
}