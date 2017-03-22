set key nobox above vertical maxrows 1
set grid
set xlabel 'Iterations'
set ylabel 'Footprint'
set format y "%2.1f"
set format x "%2.0f"
set datafile separator ","
#set xrange [1:100]
#set yrange [3900.0:4200.0]

plot 'target/data.csv' u 1:2 title 'St. deviation [-]' w lines lt rgb "#00D2F5" lw 2, \
	 'target/data.csv' u 1:3 title 'Average' w lines lt rgb "#F39F24" lw 2, \
	 'target/data.csv' u 1:4 title 'St. deviation [+]' w lines lt rgb "#13F33A" lw 2