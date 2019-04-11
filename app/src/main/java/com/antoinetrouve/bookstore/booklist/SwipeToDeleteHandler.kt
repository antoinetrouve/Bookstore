package com.antoinetrouve.bookstore.booklist

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.antoinetrouve.bookstore.Book
import com.antoinetrouve.bookstore.R

class SwipeToDeleteHandler(context: Context,
                           private val listener: SwipeListener?)
    : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val background = ColorDrawable(Color.RED)
    private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_action_delete)

    interface SwipeListener {
        fun onSwipeBook(book: Book, position: Int)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false // We don't want support moving items up / down
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // On Swipe get the book and the position
        viewHolder.let {
            val listBookViewHolder = viewHolder as BooksListAdapter.ViewHolder
            listener?.onSwipeBook(listBookViewHolder.cardView.tag as Book, listBookViewHolder.adapterPosition)
        }
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        val itemView = viewHolder.itemView // The view being swiped

        // Draw the red delete background
        background.apply {
            setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
            draw(canvas)
        }

        // Draw the delete icon
        deleteIcon?.apply {
            // Calculate position of delete icon
            val itemHeight = itemView.bottom - itemView.top
            val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
            val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
            val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
            val deleteIconRight = itemView.right - deleteIconMargin
            val deleteIconBottom = deleteIconTop + intrinsicHeight

            setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            draw(canvas)
        }

        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}
