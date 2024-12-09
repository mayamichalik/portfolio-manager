import time
from datetime import datetime
import pandas as pd
import sys

def func(stock):
    dt = datetime(2023, 1, 1)
    start_date = int(round(dt.timestamp()))

    dt = datetime(2023, 3, 31)
    end_date = int(round(dt.timestamp()))

    df = pd.read_csv(f"https://query1.finance.yahoo.com/v7/finance/download/{stock}?period1={start_date}&period2={end_date}&interval=1d&events=history&includeAdjustedClose=true",
                    parse_dates = ['Date'], index_col='Date')

    df['Date'] = df.index
    
    print(df.to_json(orient="records", date_format="iso"))



func("AAPL")
func(sys.argv[1])
