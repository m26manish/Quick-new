package com.example.musicappui.ui.theme


/*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController
import com.example.musicappui.R



@Composable

fun BrowseView(navController: NavController, modifier: Modifier = Modifier) {
    val recipeViewModel: FetchNewsViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            viewState.loading -> {
                androidx.compose.material.CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }

            viewState.error != null -> {
                androidx.compose.material.Text(text = "Error Occurred: ${viewState.error}")
            }

            else -> {

                LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                    items(viewState.list) { item ->
                        BrowserItem(
                            item = item,
                             drawable =  item.image,
                            onItemClick = {
                                navController.navigate("details/${item.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}

 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.musicappui.R

@Composable
fun BrowseView(navController: NavController, modifier: Modifier = Modifier) {
    val recipeViewModel: FetchNewsViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState
    val backgroundColor = MaterialTheme.colors.background

    // Search functionality
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val isSearchBarVisible = rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val filteredList = remember(searchQuery.value, viewState.list) {
        if (searchQuery.value.isEmpty()) {
            viewState.list
        } else {
            viewState.list.filter { it.title.contains(searchQuery.value, ignoreCase = true) }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            TopAppBar(
                backgroundColor = ColorRepository.ForestGreen,
                contentColor = Color.Black,
                elevation = 0.dp,
                title = {
                    if (isSearchBarVisible.value) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            TextField(
                                value = searchQuery.value,
                                onValueChange = { searchQuery.value = it },
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        color = MaterialTheme.colors.surface,
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                placeholder = { Text("Search...", color = Color.Gray) },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        keyboardController?.hide()
                                    }
                                ),
                                trailingIcon = {
                                    if (searchQuery.value.isNotEmpty()) {
                                        IconButton(onClick = { searchQuery.value = "" }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_clear_24),
                                                contentDescription = "Clear",
                                                tint = Color.Gray
                                            )
                                        }
                                    }
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = MaterialTheme.colors.surface,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    } else {
                        Text(
                            text = "Search Option",
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }
                },
                actions = {
                    if (!isSearchBarVisible.value) {
                        IconButton(
                            onClick = { isSearchBarVisible.value = !isSearchBarVisible.value },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_24),
                                contentDescription = "Search",
                                tint = Color.Black
                            )
                        }
                    }else{
                        IconButton(
                            onClick = { isSearchBarVisible.value = !isSearchBarVisible.value },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_close_24),
                                contentDescription = "Close",
                                tint = Color.Black
                            )
                        }
                    }

                },
                modifier = Modifier.background(
                    Brush.horizontalGradient(
                        listOf(
                            ColorRepository.YellowGreen,
                            ColorRepository.Olive,
                            ColorRepository.ForestGreen
                        ),
                        startX = 0f,
                        endX = 1000f
                    )
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            when {
                viewState.loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.Center)
                        )
                        Text(
                            text = "Loading...",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }

                viewState.error != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Error Occurred",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.error,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = viewState.error ?: "Unknown Error",
                            style = MaterialTheme.typography.body1
                        )
                        Button(
                            onClick = { /* Handle retry action */ },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Retry")
                        }
                    }
                }

                else -> {
                    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                        items( filteredList) { item ->
                            BrowserItem(
                                item = item,
                                drawable = item.image,
                                onItemClick = {
                                    navController.navigate("details/${item.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
