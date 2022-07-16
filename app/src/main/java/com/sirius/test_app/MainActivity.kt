package com.sirius.test_app

import android.graphics.drawable.Icon
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.appcompattheme.AppCompatTheme

class MainActivity : AppCompatActivity() {

    private val res: DataModel = DataModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainView = findViewById<ComposeView>(R.id.main)
        mainView.setContent {
            AppCompatTheme {
                Main()
            }
        }
    }

    @Composable
    fun Main() {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.background_main))
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(CenterHorizontally)
            ) {
                Header()
                GameTags()
                Description()
                RatingAndReview()
            }
        }

    }

    private @Composable
    fun GameTags() {
        Row(modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            res.tags.forEach {
                    Text(
                        text = it,
                        color = colorResource(id = R.color.tag_text)
                    )
            }
        }
    }

    private @Composable
    fun Header() {
        Img(url = res.image)
        Row (verticalAlignment = CenterVertically) {
            Box {
                Img(res.logo)
            }
            Column {
                Text(
                    text = res.name,
                    fontSize = 40.sp,
                    color = colorResource(id = R.color.title_text)
                )
                Row {
                    Stars()
                    Text(
                        text = res.gradeCnt,
                        color = colorResource(id = R.color.grade_cnt_title)
                    )
                }
            }
        }
    }

    private @Composable
    fun Description() {
        Text(
            text = res.description,
            color = colorResource(id = R.color.description_text),
            modifier = Modifier.alpha(0.7f)
        )
    }

    private @Composable
    fun RatingAndReview() {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Review & Ratings",
                fontSize = 20.sp,
                color = colorResource(id = R.color.title_text)
            )
            GameRating()
            Reviews()
        }
    }

    private @Composable
    fun GameRating() {
        Row {
            Text(
                text = res.rating.toString(),
                fontSize = 40.sp,
                color = colorResource(id = R.color.title_text)
            )
            Column {
                Stars()
                Text(
                    text = res.gradeCnt + " Reviews",
                    color = colorResource(id = R.color.reviews_text),
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
    }

    private @Composable
    fun Reviews() {
        Column (verticalArrangement = Arrangement.spacedBy(20.dp)) {
            res.reviews.forEach() {
                Column {
                    Row {
                        Box(modifier = Modifier.weight(1f)) {
                            AvatarImg(url = it.userImage)
                        }
                        Column(modifier = Modifier.weight(3f)) {
                            Text(
                                text = it.userName,
                                color = colorResource(id = R.color.title_text)
                            )
                            Text(
                                text = it.date,
                                color = colorResource(id = R.color.title_text),
                                modifier = Modifier.alpha(0.4f)
                            )
                        }
                    }
                    Text(
                        text = it.message,
                        color = colorResource(id = R.color.reviews_text)
                    )
                }
            }
        }
    }

    private @Composable
    fun Stars() {
        Row {
            repeat(5) {
                Icon(Icons.Rounded.Star, contentDescription = null, tint = Color.Yellow)
            }
        }
    }

    @Composable
    fun Img(url: String) {
        AsyncImage(
            model = url,
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    fun AvatarImg(url: String) {
        AsyncImage(
            model = url,
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp)
        )
    }

    @Preview
    @Composable
    fun MainPreview() {
        Main()
    }

}