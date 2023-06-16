package com.example.buzzwiseapp.data.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("similar_words")
	val similarWords: List<SimilarWordsItem>? = null
)

data class SimilarWordsItem(

	@field:SerializedName("Word")
	val word: String? = null,

	@field:SerializedName("Cosine Similarity")
	val cosineSimilarity: Any? = null
)
