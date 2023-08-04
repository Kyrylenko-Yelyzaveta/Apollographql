package com.aaaaaa.apollographql.presentation.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aaaaaa.apollographql.R
import com.aaaaaa.apollographql.databinding.CountryItemBinding
import com.aaaaaa.apollographql.domain.client.CountryItem
import com.aaaaaa.apollographql.domain.client.ExtraCountryInformationItem


class CountriesAdapter(
    private var countries: List<CountryItem>?,
    private val itemClickListener: (CountryItem, (ExtraCountryInformationItem )-> Unit) -> Unit
) :
    RecyclerView.Adapter<CountriesAdapter.CountryHolder>() {

    inner class CountryHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = CountryItemBinding.bind(item)
        fun bind(item: CountryItem
        ) = with(binding) {
            txtCurrencyMeaning.text = item.currency
            txtCapitalMeaning.text = item.capital
            txtNameMeaning.text = item.name

            Log.d("Image", item.emoji)

            imEmojiSrc.text = item.emoji

            itemView.setOnClickListener {
                itemClickListener(item) { extraData ->

                    when (extraLayout.visibility){
                        View.VISIBLE -> extraLayout.visibility= View.GONE
                        View.GONE-> {
                                var fulltext = ""
                                extraData.languages.forEach{
                                    fulltext+="\n $it"

                                }
                                languages.text = "Languages: $fulltext"
                                continent.text  ="Continent:\n ${extraData.continent}"

                                extraLayout.visibility= View.VISIBLE
                        }
                    }

                }
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesAdapter.CountryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryHolder(view)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        val itemData = countries!![position]
        holder.bind(itemData)
    }


    override fun getItemCount(): Int {
        return countries?.size ?: 0
    }
}