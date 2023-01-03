package com.academy.a05c_thread_backgroundthread_class

class Playlist {
    companion object{
        var songs = arrayOf<String>("Song 1", "Song 2", "Song 3")
            get(){
                return field
            }
            set(value){
                field= value
            }
    }
}