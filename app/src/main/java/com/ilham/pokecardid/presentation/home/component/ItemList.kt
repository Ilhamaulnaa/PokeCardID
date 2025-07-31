package com.ilham.pokecardid.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ilham.event.data.models.PokeListEntry
import com.ilham.event.utils.Constatnt
import com.ilham.pokecardid.ui.component.text.BaseText
import com.ilham.pokecardid.ui.component.text.TextLabel
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme
import com.valentinilk.shimmer.shimmer

@ExperimentalLayoutApi
@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    entry: PokeListEntry,
    onItemClick: (PokeListEntry) -> Unit = {}
) {

    val scrollState = rememberScrollState()

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data("${Constatnt.BASE_IMAGE_URL}${entry.imageUrl}")
        .crossfade(true)
        .build()

    Box(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = Color.White
            )
            .clickable {
                onItemClick(entry)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 5.dp)
        ){
            Row(verticalAlignment = Alignment.CenterVertically){
                AsyncImage(
                    model = imageRequest,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .shadow(
                            elevation = 5.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .size(70.dp),
                )
                Column(
//                    modifier = Modifier.horizontalScroll(scrollState),
                ){
                    BaseText(
                        text = entry.pokemonName,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        maxLines = 1,
                        modifier = Modifier.horizontalScroll(scrollState)
                    )
                    BaseText(
                        text = "#${entry.number}",
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingItemListSection(
    modifier: Modifier = Modifier,
    size: Int = 5
) {

    Column(modifier = modifier.shimmer()){
        repeat(size){
            Row(modifier = Modifier.fillMaxWidth()){
                LoadingItem(
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                LoadingItem(
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}

@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 0.3.dp,
                color = Color(0xFF4D4D4D),
                shape = RoundedCornerShape(10.dp)
            )
            .height(115.dp)
            .background(Color(0xFFEBE5F2))
    )
}


@ExperimentalLayoutApi
@Preview
@Composable
private fun ItemListPreview() {
    PokeCardIDTheme {
        Surface {
            val entry = PokeListEntry(
                pokemonName = "Bulbasor",
                imageUrl = "",
                number = 1,
                url = ""
            )
            ItemList(
                entry = entry,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun LoadingItemListSectionPreview() {
    PokeCardIDTheme {
        Surface {
            LoadingItemListSection(
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}