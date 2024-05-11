package com.mckapp.loginscreenuicompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    //manifeste   android:windowSoftInputMode="adjustResize" ekledim böylelikle klavye açılınca ekran dışına taşma olmuyor
    //bunun yerine smooth geçiş için adjustNothing de yazabiliriz.
    val isImeVisible by rememberImeState()

    var userName by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    GradientBox(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //ilk etapta false olarak geliyor çünkü klavye kapalı durumda.Texte tıkladığımda true değere dönüyor
                //Bundan dolayı da Welcome texti olan box ilk seferde 0.35f lik bir yer kaplıyor.Klavye açılınca 0f oluyor ve kayıp oluyor.
                val animatedUpperSectionRatio by animateFloatAsState(
                    targetValue = if (isImeVisible) 0f else 0.35f,
                    label = ""
                )

                AnimatedVisibility(visible = !isImeVisible, enter = fadeIn(), exit = fadeOut()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(animatedUpperSectionRatio),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Welcome",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //If kontrolüyle telefonun boyutuna göre boşluk bırakıyorum
                    if (isSmallScreenHeight()) {
                        Spacer(modifier = Modifier.fillMaxSize(0.05f))
                    } else {
                        //1f yapmamalıyız büyük cihazlarda kayıp oluyor
                        Spacer(modifier = Modifier.fillMaxSize(0.08f))
                    }
                    Text(
                        "Log In",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Black
                    )

                    if (isSmallScreenHeight()) {
                        Spacer(modifier = Modifier.fillMaxSize(0.05f))
                    } else {
                        Spacer(modifier = Modifier.fillMaxSize(0.08f))
                    }

                    MyTextField(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        label = "Username",
                        keyboardOptions = KeyboardOptions(),
                        keyboardActions = KeyboardActions(),
                        textChanger = {
                            userName=it
                        },
                        textValue = userName,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    MyTextField(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        label = "Password",
                        keyboardOptions = KeyboardOptions(),
                        keyboardActions = KeyboardActions(),
                        trailingIcon = Icons.Default.Lock,
                        textChanger = {
                            password=it
                        },
                        textValue = password
                    )
                    if (isImeVisible) {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .padding(horizontal = 16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0054D3),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "Log In",
                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(500))
                            )
                        }
                    } else {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .padding(horizontal = 16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0054D3),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "Log In",
                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(500))
                            )
                        }
                    }
                }
            }
        }
    )
}