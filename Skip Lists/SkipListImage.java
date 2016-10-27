
// I spent a good bit of time writing this to help solve my bugs
// Since I already made it, might as well share it.

// The following are code that you can put within the Remove and Put method,
// near the end so that it'll print out what the SkipList looks like
// everytime it Adds (put) or remove something.
// Use at your own Discretion
// I'm pretty sure there aren't any bugs, but I could be wrong.
// !!!!!!!!!!!!!!!! MAKE SURE TO DELETE BEFORE SUBMITTING!!!!!!!!!!!!!!
~Enjoy

------------------------------- PUT------------------------------
// Put this near the beginning of your PUT method,
// Right after you find out what level you want to put your data in
// I had a variable name "level" to tell me what level I wanted to put the
// data in. Change "level" to whatever variable you have as the level.
    System.out.println("ADDING " + data + " at level "
        + level + "\n______________________");



// Put this in the end of your PUT method. Preferably after incrementing size
// This prints out what your SkippedList looks like
// MAKE SURE YOU HAVE NO VARIABLE NAMED "tNode" or "tempNode"
    // if you did, change all the tNode here to something else;
            Node<T> tNode = head;
            Node<T> tempNode = head;
            String output = "***** ADDED " + data + at
                    + " *****\n" + "---Levels---\n";
            for (int l = 1; l <= head.getLevel(); l++) {
                output = output + String.format("%-6s","|__" + l + "__|");
            }
            output = output + "\n";
            while (tNode != null && tNode.getDown() != null) {
                tNode = tNode.getDown();
            }

            while (tNode != null && tNode.getNext() != null) {
                tempNode = tNode.getNext();
                boolean change = false;
                while (tempNode != null ) {

                    output = output + String.format("%-4s", "   " + tempNode.getData() + "   ");
                    tempNode = tempNode.getUp();
                    change = true;
                }
                if (change) {
                    output = output + "\n";
                }
                tNode = tNode.getNext();

            }
            System.out.print(output);
            System.out.println("Size is : " + size + "\n ~~~~~~~~~~~~~~~~~~~~~");
        }


----------------------------REMOVE----------------------------------------
// This is almost identical to add, except this says removed.


        Node<T> tNode = head;
        Node<T> tempNode = head;
        String output = "***** REMOVED " + data
                + " *****\n" + "---Levels---\n";
        for (int l = 1; l <= head.getLevel(); l++) {
            output = output + String.format("%-6s","|__" + l + "__|");
        }
        output = output + "\n";
        int i = head.getLevel();
        while (tNode != null && tNode.getDown() != null) {
            tNode = tNode.getDown();
        }

        while (tNode != null && tNode.getNext() != null) {
            tempNode = tNode.getNext();
            boolean change = false;
            while (tempNode != null) {
                output = output + String.format("%-4s", "   " + tempNode.getData() + "   ");
                tempNode = tempNode.getUp();
                change = true;
            }
            if (change) {
                output = output + "\n";
            }
            tNode = tNode.getNext();

        }
        System.out.println(output);
        System.out.println("Size is : " + size);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


------------------------MAIN METHOD---------------------------------
//You can test your skiplist

    public static void main(String[] args) {
        CoinFlipper random = new CoinFlipper();
        SkipList<Integer> list = new SkipList<Integer>(random);
        list.remove(2);
        list.put(1);
        list.put(2);
        list.remove(1);
        list.put(7);
        list.clear();
        System.out.println("Trying to get 1:" + list.get(1));
        list.put(7);
        list.remove(99);
        list.put(4);
        System.out.println("Trying to get 4:" + list.get(4));
        list.put(8);
        list.put(6);
        list.put(5);
        list.put(9);

    }

//After running the above, I get the following results:
***** REMOVED 2 *****
---Levels---
|__1__|

Size is : 0
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ADDING 1 at Level: 3.........
***** ADDED 1 *****
---Levels---
|__1__||__2__||__3__|
   1      1      1
Size is : 1
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ADDING 2 at Level: 1.........
***** ADDED 2 *****
---Levels---
|__1__||__2__||__3__|
   1      1      1
   2
Size is : 2
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
***** REMOVED 1 *****
---Levels---
|__1__|
   2

Size is : 1
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ADDING 7 at Level: 2.........
***** ADDED 7 *****
---Levels---
|__1__||__2__|
   2
   7      7
Size is : 2
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Trying to get 1:null
ADDING 7 at Level: 1.........
***** ADDED 7 *****
---Levels---
|__1__|
   7
Size is : 1
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
***** REMOVED 99 *****
---Levels---
|__1__|
   7

Size is : 1
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ADDING 4 at Level: 2.........
***** ADDED 4 *****
---Levels---
|__1__||__2__|
   4      4
   7
Size is : 2
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Trying to get 4:4
ADDING 8 at Level: 3.........
***** ADDED 8 *****
---Levels---
|__1__||__2__||__3__|
   4      4
   7
   8      8      8
Size is : 3
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ADDING 6 at Level: 1.........
***** ADDED 6 *****
---Levels---
|__1__||__2__||__3__|
   4      4
   6
   7
   8      8      8
Size is : 4
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ADDING 5 at Level: 1.........
***** ADDED 5 *****
---Levels---
|__1__||__2__||__3__|
   4      4
   5
   6
   7
   8      8      8
Size is : 5
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ADDING 9 at Level: 2.........
***** ADDED 9 *****
---Levels---
|__1__||__2__||__3__|
   4      4
   5
   6
   7
   8      8      8
   9      9
Size is : 6
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

