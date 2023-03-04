package com.example.scalecaching.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ContactItemRow(
    item: ContactItem,
    todoListState: State<List<ContactItem>>,
    viewModel: ContactViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            text = item.name
        )
        Checkbox(
            checked = item.friend,
            onCheckedChange = {
                val index = todoListState.value.indexOf(item)
                viewModel.setFriend(index, it)
            }
        )
    }
}
