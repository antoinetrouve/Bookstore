package com.antoinetrouve.bookstore.repository

import com.antoinetrouve.bookstore.Book
import timber.log.Timber

private const val INDEX_OFFSET = 2

private val BOOKS = listOf(
    Book(0, "The Lord of The Rings", "J.R.R Tolkien",
        "The Lord of the Rings is an epic high fantasy novel written by English author and scholar J. R. R. Tolkien. The story began as a sequel to Tolkien's 1937 fantasy novel The Hobbit, but eventually developed into a much larger work. Written in stages between 1937 and 1949, The Lord of the Rings is one of the best-selling novels ever written, with over 150 million copies sold",
        "https://upload.wikimedia.org/wikipedia/en/e/e9/First_Single_Volume_Edition_of_The_Lord_of_the_Rings.gif"),
    Book(0, "Ubik", "Phillip K. Dick",
        "By the year 1992, humanity has colonized the Moon and psychic powers are common. The protagonist, Joe Chip, is a debt-ridden technician working for Runciter Associates, a \"prudence organization\" employing \"inertials\"—people with the ability to negate the powers of telepaths and \"precogs\"—to enforce the privacy of clients. The company is run by Glen Runciter, assisted by his deceased wife Ella who is kept in a state of \"half-life\", a form of cryonic suspension that allows the deceased limited consciousness and ability to communicate. While consulting with Ella, Runciter discovers that her consciousness is being invaded by another half-lifer, Jory Miller.",
        "https://upload.wikimedia.org/wikipedia/en/a/af/Ubik%281stEd%29.jpg"),
    Book(0, "Harry Potter: vol 1", "J.K Rowling",
        "Harry Potter has been living an ordinary life, constantly abused by his surly and cold aunt and uncle, Vernon and Petunia Dursley and bullied by their spoiled son Dudley since the death of his parents ten years prior. His life changes on the day of his eleventh birthday when he receives a letter of acceptance into a Hogwarts School of Witchcraft and Wizardry, delivered by a half-giant named Rubeus Hagrid after previous letters had been destroyed by Harry’s Uncle Vernon and his Aunt Petunia. Hagrid explains Harry's hidden past as the wizard son of James and Lily Potter, who were a wizard and witch respectively, and how they were murdered by the most evil and powerful dark wizard of all time, Lord Voldemort, which resulted in the one-year-old Harry being sent to live with his aunt and uncle. The strangest bit of the murder was how Voldemort was unable to kill him, but instead had his own powers removed and blasted away, sparking Harry's immense fame among the magical community.",
        "https://upload.wikimedia.org/wikipedia/en/6/6b/Harry_Potter_and_the_Philosopher%27s_Stone_Book_Cover.jpg"),
    Book(0, "A Song of Ice And Fire", "George R.R. Martin",
        "A Song of Ice and Fire takes place on the fictional continents Westeros and Essos. The point of view of each chapter in the story is a limited perspective of a range of characters growing from nine, in the first novel, to 31 characters by the fifth novel. Three main stories interweave: a dynastic war among several families for control of Westeros, the rising threat of the supernatural Others in the northernmost reaches of Westeros, and the ambition of Daenerys Targaryen, the deposed king's exiled daughter, to assume the Iron Throne.",
        "https://upload.wikimedia.org/wikipedia/en/d/dc/A_Song_of_Ice_and_Fire_book_collection_box_set_cover.jpg"),
    Book(0, "Dune", "Frank Herbert",
        "In the far future, humanity has eschewed advanced computers in favor of adapting their minds to be capable of extremely complex tasks. Much of this is enabled by the spice melange, which is only found on Arrakis, a desert planet with giant sandworms as its most notable native lifeform. Melange improves general health, extends life and can bestow limited prescience, and its rarity makes it a form of currency in the interstellar empire. Melange allows the Spacing Guild's Navigators to safely route faster-than-light travel between planets, and helps the Reverend Mothers of the matriarchal Bene Gesserit to access their Other Memory, the ego and experiences of their female ancestors.",
        "https://upload.wikimedia.org/wikipedia/en/d/de/Dune-Frank_Herbert_%281965%29_First_edition.jpg"),
    Book(0, "The Hounds Of the Baskerville", "Arthur Conan Doyle",
        "Dr. James Mortimer asks Sherlock Holmes for advice following the death of his friend, Sir Charles Baskerville. Sir Charles was found dead on the grounds of his Devonshire estate, Baskerville Hall. Mortimer now fears for Sir Charles's nephew and sole heir, Sir Henry Baskerville, who is the new master of Baskerville Hall. The death was attributed to a heart attack, but Mortimer is suspicious, because Sir Charles died with an expression of horror on his face, and Mortimer noticed \"the footprints of a gigantic hound\" about 50 yards from where Sir Charles lay dead. The Baskerville family has supposedly been under a curse since the era of the English Civil War when ancestor Hugo Baskerville allegedly offered his soul to the devil for help in abducting a woman and was reportedly killed by a giant spectral hound. Sir Charles believed in the curse and was apparently fleeing from something in fright when he died.",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Cover_%28Hound_of_Baskervilles%2C_1902%29.jpg/800px-Cover_%28Hound_of_Baskervilles%2C_1902%29.jpg")
)

class FakeBookApi {

    companion object {
        private var bookIndex = 0
    }

    /**
     * Allow to get books to simulate API behavior
     */
    fun loadBooks() : List<Book> {
        val bookSize = BOOKS.size
        var newBookIndex = (bookIndex + INDEX_OFFSET) % bookSize

        if (newBookIndex == 0) {
            newBookIndex = bookSize
        }

        Timber.i("Book range : $bookIndex / $newBookIndex")
        val bookSlice = BOOKS.subList(bookIndex, newBookIndex)

        bookIndex = newBookIndex % bookSize

        return bookSlice
    }
}