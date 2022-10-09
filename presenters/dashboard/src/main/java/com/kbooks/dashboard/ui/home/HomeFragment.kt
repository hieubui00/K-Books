package com.kbooks.dashboard.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import coil.compose.rememberAsyncImagePainter
import com.kma.kbooks.resources.ui.theme.KBooksTheme

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            KBooksTheme {
                this@HomeFragment.Content()
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun Content() {
        Box {
            Scaffold(
                topBar = {
                    Column() {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(

                                onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                            }
                            Text(
                                modifier = Modifier.padding(start = 60.dp),
                                text = "Trang chủ", fontWeight = FontWeight.Bold
                            )
                            Row() {
                                IconButton(

                                    onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = null
                                    )
                                }
                                IconButton(

                                    onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null
                                    )
                                }
                            }

                        }
                    }
                },
                bottomBar = {},
                drawerContent = {},
            ) {

                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(40.dp)
                                .border(1.dp, Color.Black)
                        ) {
                            Text(
                                modifier = Modifier.align(alignment = Alignment.Center),
                                text = "Nổi bật", fontSize = 14.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(40.dp)
                                .border(1.dp, Color.Black)
                        ) {
                            Text(
                                modifier = Modifier.align(alignment = Alignment.Center),
                                text = "Tất cả", fontSize = 14.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(40.dp)
                                .border(1.dp, Color.Black)
                        ) {
                            Text(
                                modifier = Modifier.align(alignment = Alignment.Center),
                                text = "Mới cập nhập", fontSize = 14.sp
                            )
                        }


                    }
                    Column(
                        // in this column we are adding modifier
                        // to fill max size, mz height and max width
                        modifier = Modifier.fillMaxSize()
                            .fillMaxWidth()
                            .height(300.dp)
                            // on below line we are adding
                            // padding from all sides.
                            .padding(10.dp),
                        // on below line we are adding vertical
                        // and horizontal arrangement.
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // on below line we are adding image for our image view.
                        Image(
                            // on below line we are adding the image url
                            // from which we will  be loading our image.
                            painter = rememberAsyncImagePainter("https://cdn.sforum.vn/sforum/wp-content/uploads/2022/09/app-do%CC%A3c-sa%CC%81ch-mie%CC%82%CC%83n-phi%CC%81-cover.jpeg"),

                            // on below line we are adding content
                            // description for our image.
                            contentDescription = "gfg image",

                            // on below line we are adding modifier for our
                            // image as wrap content for height and width.
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxWidth()
                                .fillMaxHeight()
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {


                        Text(
                            //modifier = Modifier.align(alignment = Alignment.Center),
                            text = "Mới Nhất", fontSize = 20.sp
                        )


                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(

                                text = "Tất cả", fontSize = 14.sp
                            )
                            IconButton(

                                onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            }
                        }


                    }

                    LazyRow(modifier = Modifier.padding(10.dp)) {
                        items(10) {
                            Column(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(200.dp)
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    // on below line we are adding the image url
                                    // from which we will  be loading our image.
                                    painter = rememberAsyncImagePainter("https://cdn-amz.woka.io/images/I/81R2N4PRuUL.jpg"),

                                    // on below line we are adding content
                                    // description for our image.
                                    contentDescription = "gfg image",

                                    // on below line we are adding modifier for our
                                    // image as wrap content for height and width.
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .wrapContentHeight()
                                        .wrapContentWidth()
                                )
                                Text(

                                    text = "Tiêu đề ",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(

                                    text = "Tác giả", fontSize = 12.sp
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {


                        Text(
                            //modifier = Modifier.align(alignment = Alignment.Center),
                            text = "Sách hay mỗi ngày", fontSize = 20.sp
                        )


                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(

                                text = "Tất cả", fontSize = 14.sp
                            )
                            IconButton(

                                onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            }
                        }


                    }

                    LazyRow(modifier = Modifier.padding(10.dp)) {
                        items(10) {
                            Column(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(200.dp)
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    // on below line we are adding the image url
                                    // from which we will  be loading our image.
                                    painter = rememberAsyncImagePainter("https://cdn-amz.woka.io/images/I/81R2N4PRuUL.jpg"),

                                    // on below line we are adding content
                                    // description for our image.
                                    contentDescription = "gfg image",

                                    // on below line we are adding modifier for our
                                    // image as wrap content for height and width.
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .wrapContentHeight()
                                        .wrapContentWidth()
                                )
                                Text(

                                    text = "Tiêu đề ",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(

                                    text = "Tác giả", fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }


        }
    }
}
