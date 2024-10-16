package com.example.myshopping

//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align


//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun myShopping (){

    var sItems by remember{ mutableStateOf(listOf<ShoppingList>()) }
    var showDialog by remember{ mutableStateOf(false) }
    var itemsName by remember { mutableStateOf( "") }
    var itemsQuantity by remember { mutableStateOf( "") }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,

       horizontalAlignment = Alignment.CenterHorizontally,

    ){
        Button(onClick = { showDialog = true },
         modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Add Items")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),


        ) {
          items(sItems){
              ShoppingListItems (it , {}, {})
          }
        }
    }
    if (showDialog){
        AlertDialog(onDismissRequest = { showDialog = false },
            confirmButton = {
             Row (
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(8.dp),
                 horizontalArrangement = Arrangement.SpaceBetween)
             {
                 Button(onClick = {
                   if (itemsName.isNotBlank()){
                       val newItem = ShoppingList(
                           id = sItems.size+1,
                           name = itemsName,
                           quantity = itemsQuantity.toInt()
                       )
                       sItems =  sItems + newItem
                       showDialog = false
                   }
                 },

                 ) {
                     Text(text = "Add")
                 }
                 Button(onClick = { showDialog = false }) {
                     Text(text = "Cancel")
                 }
             }
                            },

            title = { Text(text = "Add Shopping Items",
                //contentAlignment = Alignment.Center ,

                fontSize = 20.sp, fontWeight = FontWeight.Black
            )},
            text = {
                Column(

                ){
                OutlinedTextField(value = itemsName,
                    onValueChange = {itemsName = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text(text = "Enter Items Name",
                    )
                    } )
                    OutlinedTextField(value = itemsQuantity,
                        onValueChange = {itemsQuantity = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        label = { Text(text = "Enter Quantity")
                        } )
                }
            }
        
        )

    }


}
data class ShoppingList(
    var id : Int,
    var quantity : Int,
    var name : String,
    var isEditing : Boolean = false

){

}

@Composable
fun ShoppingListItems (
    item : ShoppingList,
    onEditClick : () -> Unit,
    onDeleteClick : () -> Unit,
){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .border(
            border = BorderStroke(2.dp, Color(color = 0XFF018786)),
            shape = RoundedCornerShape(20)
        )

             ){
   Text(text = item.name, modifier = Modifier.padding(8.dp))
    }



}




