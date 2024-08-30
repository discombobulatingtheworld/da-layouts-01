package com.example.ucu2024_android_layouts_leccion1

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.arrayMapOf
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.ucu2024_android_layouts_leccion1.ui.theme.Ucu2024androidlayoutsleccion1Theme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ucu2024androidlayoutsleccion1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SSOLogin(providerName: String, imageResourceId: Int) {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.dp, color = Color.Transparent),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Transparent,
            containerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = providerName,
            modifier = Modifier
                .size(70.dp)
        )
    }
}

@Composable
fun SSOBar(providers: Map<String, Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        providers.forEach {
            SSOLogin(providerName = it.key, imageResourceId = it.value)
        }
    }
}

@Composable
fun LoginSSO(providers: Map<String, Int>) {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Or login with")
        SSOBar(providers)
    }
}

@Composable
fun LoginOptions() {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TextButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(text = "Don't have an account?")
        }
        TextButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(text = "Sign-up")
        }
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        border = BorderStroke(1.dp, color = Color.Gray),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(5.dp),
    ) {
        Text(
            text = "LOGIN",
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun LoginActions(formState: LoginState) {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp)
    ) {
        LoginButton(formState.clear)
        LoginOptions()
    }
}

@Composable
fun LoginInputField(text: String, placeholder: String, onValueChange: (String) -> Unit, isPassword: Boolean = false) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            Text(text = placeholder)
        },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(1f),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
    )
}

@Composable
fun LoginInputs(form: LoginForm) {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp)
    ) {
        LoginInputField(text = form.email?.value?:"" , placeholder = "Email input", onValueChange = { newValue -> form.email?.value = newValue })
        LoginInputField(text = form.pass?.value?:"", placeholder = "Password input", onValueChange = { newValue -> form.pass?.value = newValue }, true)
        Row(
            modifier = Modifier
                .fillMaxWidth(1f),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(text = "Forgot password?")
            }
        }
    }
}

@Composable
fun LoginPanel(formState: LoginState) {
    Column(
         modifier = Modifier
             .padding(20.dp)
    ) {
        LoginInputs(formState.form)
        LoginActions(formState)
        LoginSSO(formState.ssoProviders)
    }
}

@Composable
fun LoginBanner() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.login_banner),
            contentDescription = "Login!",
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.lighting(Color.Gray, Color.Black),
            modifier = Modifier
                .fillMaxWidth(1f),
        )
        Text(
            text = "Log in",
            fontSize = TextUnit(7f, TextUnitType.Em),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp)
        )
    }
}

class LoginForm(
) {
    var email: MutableState<String>? = null
    var pass: MutableState<String>? = null
}

class LoginState() {
    val form: LoginForm = LoginForm()
    val ssoProviders = mapOf<String, Int>(
        "Google" to R.drawable.google,
        "Facebook" to R.drawable.facebook,
    )

    val clear: () -> Unit = {
        this.form.email?.value = ""
        this.form.pass?.value = ""
    }
}

@Composable
fun LoginView(modifier: Modifier) {
    val formState = LoginState()
    formState.form.email = remember { mutableStateOf<String>("") }
    formState.form.pass = remember { mutableStateOf<String>("") }

    Column() {
        LoginBanner()
        LoginPanel(formState)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginButtonPreview() {
    LoginView(modifier = Modifier.padding(5.dp))
}