package com.antoinetrouve.bookstore.booklist

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.antoinetrouve.bookstore.R

class SwipeToDeleteHandler(context: Context,
                           private val listener: SwipeListener?)
    : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT + ItemTouchHelper.RIGHT) {

    private val background = ColorDrawable(Color.RED)
    private val icon = ContextCompat.getDrawable(context, R.drawable.ic_delete_white_24dp)

    interface SwipeListener {
        fun onSwipeBook(position: Int)
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
        // On Swipe get the position
        viewHolder.let {
            listener?.onSwipeBook((viewHolder as BooksListAdapter.ViewHolder).adapterPosition)
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
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView // The view being swiped
        val backgroundCornerOffset = 30 // Keep background behind the rounded corners of itemView

        // calc top and bottom dimension
         val iconTop = itemView.top + (itemView.height - icon!!.intrinsicHeight) / 2
         val iconBottom = iconTop + icon.intrinsicHeight

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            when {
                // swipe to the right
                dX > 0 -> {

                    // Draw the red delete background
                    background.apply {
                        setBounds(
                            itemView.left,
                            itemView.top,
                            itemView.left + dX.toInt() + backgroundCornerOffset,
                            itemView.bottom
                        )
                        // /!\ Draw the background before the icon
                        draw(canvas)
                    }

                    // Draw the icon
                    icon.apply {
                        // Calculate position of delete icon
                        val iconMargin = (itemView.height + icon.intrinsicHeight) / 2
                        val iconLeft = itemView.left + iconMargin - icon.intrinsicWidth
                        val iconRight = itemView.left + iconMargin

                        setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        draw(canvas)
                    }
                }
                dX < 0 -> { // swipe to the left
                    // Draw the red delete background
                    background.apply {
                        setBounds(
                            itemView.right + dX.toInt() - backgroundCornerOffset,
                            itemView.top,
                            itemView.right,
                            itemView.bottom
                        )
                        draw(canvas)
                    }

                    // Draw the icon
                    icon.apply {
                        // Calculate position of delete icon
                        val iconMargin = (itemView.height - icon.intrinsicHeight) / 2
                        val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
                        val iconRight = itemView.right - iconMargin

                        setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        draw(canvas)
                    }
                }
                else -> background.setBounds(0, 0, 0, 0) // view is not swiped
            }
        }
    }
}
