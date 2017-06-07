import csv
import pandas as pd
distance_file = "distance.csv"
assignment_file = "assignment.csv"
transition_file = "transition.csv"

distance_df = pd.read_csv(distance_file, dtype=str, index_col=[0])
assignment_df = pd.read_csv(assignment_file, dtype=str)
transition_df = pd.read_csv(transition_file, dtype=str)
transition_df["Count"] = transition_df["Count"].astype(int)

total_dist = 0
for i in range(len(transition_df)):
    src_course = transition_df["Source course"][i]
    src_class = assignment_df[assignment_df["course"]==src_course]["classroom"].iloc[0]
    dest_course = transition_df["Dest course"][i]
    dest_class = assignment_df[assignment_df["course"]==dest_course]["classroom"].iloc[0]
    count = transition_df["Count"][i]
    dist = int(distance_df[src_class][dest_class])
    total_dist += dist*count

print "Total distance:", total_dist
