package com.example.eksamensprojekt_neveranother.viewmodel

import com.example.eksamensprojekt_neveranother.data.BasketItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CartViewModelTest {

    private lateinit var viewModel: CartViewModel

    @Before
    fun setup() {
        viewModel = CartViewModel()
    }

    @Test
    fun `addItem adds item to list`() {
        val item = BasketItem("Test Product", "White", "100", 0)
        viewModel.addItem(item)
        assertEquals(1, viewModel.items.size)
        assertEquals(item, viewModel.items[0])
    }

    @Test
    fun `deleteItem removes item from list`() {
        val item = BasketItem("Test Product", "White", "100", 0)
        viewModel.addItem(item)
        viewModel.deleteItem(item)
        assertTrue(viewModel.items.isEmpty())
    }

    @Test
    fun `getBtnText returns correct text based on state`() {
        // Case 1: Items in basket
        viewModel.addItem(BasketItem("Test", "Color", "100", 0))
        assertEquals("Check ud", viewModel.getBtnText(isTailored = false))
        assertEquals("Check ud", viewModel.getBtnText(isTailored = true))

        // Case 2: No items, but tailored
        viewModel.deleteItem(viewModel.items[0])
        assertEquals("Se din BH", viewModel.getBtnText(isTailored = true))

        // Case 3: No items, not tailored
        assertEquals("Skræddersy BH", viewModel.getBtnText(isTailored = false))
    }

    @Test
    fun `getBtnNavigation returns correct destination`() {
        // Case 1: Items in basket
        viewModel.addItem(BasketItem("Test", "Color", "100", 0))
        assertEquals("checkout", viewModel.getBtnNavigation(isTailored = false))

        // Case 2: No items, but tailored
        viewModel.deleteItem(viewModel.items[0])
        assertEquals("product", viewModel.getBtnNavigation(isTailored = true))

        // Case 3: No items, not tailored
        assertEquals("tailor_start", viewModel.getBtnNavigation(isTailored = false))
    }
}
