package com.task.sample.adapter


import android.view.View
import com.google.common.truth.Truth
import com.google.gson.JsonArray
import com.task.sample.JsonReader
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProductCategoriesAdapterTest {

    // region VARIABLES
    private lateinit var productCategoriesAdapterTest: ProductCategoriesAdapter
    private var listOfCategories: JsonArray? = null
    private lateinit var clickListener: View.OnClickListener
    // end region VARIABLES

    // region SETUP
    @Before
    fun setUp() {
        clickListener = mock(View.OnClickListener::class.java)
        listOfCategories = JsonReader.readJsonArray(this, "success_categories.json")
        productCategoriesAdapterTest = ProductCategoriesAdapter(listOfCategories, clickListener)
    }
    // end region SETUP

    // region TESTCASES
    //[unitUnderTest]_[input]_[ConditionToBeMet]
    @Test
    fun itemCount_checkItemCountInAdapter_countShouldBe4() {
        Truth.assertThat(productCategoriesAdapterTest.itemCount).isEqualTo(4)
    }

}